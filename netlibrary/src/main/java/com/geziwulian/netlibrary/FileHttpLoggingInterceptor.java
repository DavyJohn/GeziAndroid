package com.geziwulian.netlibrary;


import com.geziwulian.netlibrary.utils.TokenUntil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import okhttp3.internal.Platform;
import okio.Buffer;


/**
 * Created by yyx on 16/3/2.
 */
public class FileHttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final String JSON_TYPE = "application/json";
    private static final String FILE_TYPE1 = "application/x-www-form-urlencoded";
    private static final String FILE_TYPE = "multipart/form-data";

    public enum Level {
        /**
         * No logs.
         */
        NONE,
        /**
         * Logs request and response lines.
         * <p/>
         * Example:
         * <pre>{@code
         * --> POST /greeting HTTP/1.1 (3-byte body)
         * <p/>
         * <-- HTTP/1.1 200 OK (22ms, 6-byte body)
         * }</pre>
         */
        BASIC,
        /**
         * Logs request and response lines and their respective headers.
         * <p/>
         * Example:
         * <pre>{@code
         * --> POST /greeting HTTP/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         * --> END POST
         * <p/>
         * <-- HTTP/1.1 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         * <-- END HTTP
         * }</pre>
         */
        HEADERS,
        /**
         * Logs request and response lines and their respective headers and bodies (if present).
         * <p/>
         * Example:
         * <pre>{@code
         * --> POST /greeting HTTP/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         * <p/>
         * Hi?
         * --> END GET
         * <p/>
         * <-- HTTP/1.1 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         * <p/>
         * Hello!
         * <-- END HTTP
         * }</pre>
         */
        BODY
    }

    public interface Logger {
        void log(String message);

        /**
         * A {@link Logger} defaults output appropriate for the current platform.
         */
        Logger DEFAULT = new Logger() {
            @Override
            public void log(String message) {
                Platform.get().log(message);            }
        };
    }

    public FileHttpLoggingInterceptor() {
        this(Logger.DEFAULT);
    }

    public FileHttpLoggingInterceptor(Logger logger) {
        this.logger = logger;
    }

    private final Logger logger;

    private volatile Level level = Level.BODY;

    /**
     * Change the level at which this interceptor logs.
     */
    public FileHttpLoggingInterceptor setLevel(Level level) {
        if (level == null) throw new NullPointerException("level == null. Use Level.NONE instead.");
        this.level = level;
        return this;
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Level level = this.level;
        String token_str = TokenUntil.getToken();
        Request request = chain.request()
                .newBuilder()
                .addHeader("Accept","application/vnd.ibang12345.v1+json")
                .addHeader("Authorization",token_str )
                .addHeader("Content-Type", FILE_TYPE).build();

        if (level == Level.NONE) {
            return chain.proceed(request);
        }

        boolean logBody = level == Level.BODY;
        boolean logHeaders = logBody || level == Level.HEADERS;

        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;

        String requestStartMessage = request.method() + ' ' + request.url();
        if (!logHeaders && hasRequestBody) {
            requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
        }
        logger.log(requestStartMessage);

        if (logHeaders) {

            if (!logBody || !hasRequestBody) {
                logger.log("--> END " + request.method());
            } else if (bodyEncoded(request.headers())) {
                logger.log("--> END " + request.method() + " (encoded body omitted)");
            } else if (request.body() instanceof MultipartBody) {
                //如果是MultipartBody，会log出一大推乱码的东东
            } else {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    contentType.charset(UTF8);
                }

                logger.log(buffer.readString(charset));

                logger.log(request.method() + " (" + requestBody.contentLength() + "-byte body)");
            }
        }


        long startNs = System.nanoTime();
        okhttp3.Response response = chain.proceed(request);
//        ResponseBody responseBody = response.body();
        String str = response.body().string();
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        logger.log(response.code() +"|"+ ' ' + response.message() + "\n"+str + " (" + tookMs + "ms" + ')'+"\n"+"this token:"+token_str);
//        Headers headers = response.headers();
        if (response.header("Authorization") != null) {
            logger.log("refreshedToken:"+response.header("Authorization"));
            logger.log("---------------rbefor:" + TokenUntil.getToken());
            TokenUntil.saveToken(response.header("Authorization"));
            logger.log("---------------rafter:" + TokenUntil.getToken());
        }
        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), str))
                .build();
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    private static String protocol(Protocol protocol) {
        return protocol == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1";
    }
}
