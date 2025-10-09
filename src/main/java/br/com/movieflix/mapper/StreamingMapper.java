package br.com.movieflix.mapper;

import br.com.movieflix.entity.Streaming;
import br.com.movieflix.request.StreamingRequest;
import br.com.movieflix.response.StreamingResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequest request){
        return Streaming
                .builder()
                .name(request.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming){
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }

}
