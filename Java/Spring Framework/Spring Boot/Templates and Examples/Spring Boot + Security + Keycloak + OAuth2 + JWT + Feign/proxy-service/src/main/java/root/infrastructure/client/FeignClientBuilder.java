package root.infrastructure.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.QueryMapEncoder;
import feign.Request;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(fluent = true)
public class FeignClientBuilder<T>
{

    private final Class<T> apiType;
    private final ObjectMapper mapper;
    private final String baseApiUri;

    private Logger.Level logLevel = Logger.Level.BASIC;
    private Encoder encoder;
    private Decoder decoder;
    private QueryMapEncoder queryMapEncoder;
    private ErrorDecoder errorDecoder = new ErrorDecoder.Default();
    private RequestInterceptor requestInterceptor;
    private Contract contract;

    public FeignClientBuilder(Class<T> apiType, ObjectMapper mapper, String baseApiUri)
    {
        this.apiType = apiType;
        this.mapper = mapper;
        this.baseApiUri = baseApiUri;
    }

    public T build()
    {
        Feign.Builder builder = Feign.builder()
            .logLevel(logLevel)
            .client(new OkHttpClient())
            .logger(new Slf4jLogger(apiType))
            .encoder(encoder != null ? encoder : new JacksonEncoder(mapper))
            .decoder(decoder != null ? decoder : new JacksonDecoder(mapper))
            .queryMapEncoder(queryMapEncoder != null ? queryMapEncoder : new CustomBeanQueryMapEncoder())
            .options(new Request.Options(30_000, 60_000))
            .errorDecoder(errorDecoder);
        if (requestInterceptor != null)
        {
            builder.requestInterceptor(requestInterceptor);
        }
        if (contract != null)
        {
            builder.contract(contract);
        }
        return builder.target(apiType, baseApiUri);
    }
}
