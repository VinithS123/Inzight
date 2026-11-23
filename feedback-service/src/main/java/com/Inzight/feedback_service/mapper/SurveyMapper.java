package com.Inzight.feedback_service.mapper;

import com.Inzight.feedback_service.dto.SurveyDto;
import com.Inzight.feedback_service.entity.SurveyEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface SurveyMapper {

    SurveyDto toSurveyDto(SurveyEntity surveyEntity);

    SurveyEntity toSurveyEntity(SurveyDto surveyDto);

    List<SurveyEntity> toListSurveyEntity(List<SurveyDto> surveyDtos);

    List<SurveyDto> toListSurveyDto(List<SurveyEntity> surveyEntities);

}
