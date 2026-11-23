package com.Inzight.feedback_service.mapper;

import com.Inzight.feedback_service.dto.FeedbackDto;
import com.Inzight.feedback_service.entity.FeedbackEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    FeedbackDto toFeedbackDto(FeedbackEntity feedBackEntity);

    FeedbackEntity toFeedBackEntity(FeedbackDto feedbackDto);

    List<FeedbackDto> toFeedbackDtoList(List<FeedbackEntity> feedbackEntityList);

}
