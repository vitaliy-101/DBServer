package com.example.datamanagementserver.mapper;

import com.example.datamanagementserver.dto.Year.YearRequest;
import com.example.datamanagementserver.dto.Year.YearResponse;
import com.example.datamanagementserver.dto.auditory.AuditoryResponse;
import com.example.datamanagementserver.dto.corpus.CorpusRequest;
import com.example.datamanagementserver.dto.corpus.CorpusResponse;
import com.example.datamanagementserver.dto.direction.DirectionRequest;
import com.example.datamanagementserver.dto.direction.DirectionResponse;
import com.example.datamanagementserver.dto.event.EventResponse;
import com.example.datamanagementserver.dto.group.GroupRequest;
import com.example.datamanagementserver.dto.group.GroupResponse;
import com.example.datamanagementserver.dto.user.UserResponse;
import com.example.datamanagementserver.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DataMapper {

    List<DirectionResponse> convertFromDirectionListToResponse(List<Direction> directions);

    DirectionResponse convertFromDirectionToResponse(Direction direction);

    Direction convertFromDirectionRequestToEntity(DirectionRequest direction);

    YearResponse convertFromYearToResponse(Year year);
    List<YearResponse> convertFromYearListToResponse(List<Year> years);

    Year convertFromYearRequestToEntity(YearRequest yearRequest);

    @Mapping(target = "directionId", qualifiedByName = "convertDirectionToId", source = "direction")
    @Mapping(target = "yearId", qualifiedByName = "convertYearToId", source = "year")
    GroupResponse convertFromGroupToResponse(Group group);

    List<GroupResponse> convertFromGroupListToResponse(List<Group> groups);

    @Named("convertDirectionToId")
    default Long convertDirectionToId(Direction direction) {
        if (direction == null) {
            return null;
        }
        return direction.getId();
    }

    @Named("convertYearToId")
    default Long convertYearToId(Year year) {
        if (year == null) {
            return null;
        }
        return year.getId();
    }

    @Mapping(target = "groupId", qualifiedByName = "convertGroupToId", source = "group")
    UserResponse convertFromUserToResponse(User user);

    List<UserResponse> convertFromUserListToResponse(List<User> users);

    @Named("convertGroupToId")
    default Long convertGroupToId(Group group) {
        if (group == null) {
            return null;
        }
        return group.getId();
    }

    CorpusResponse convertFromCorpusToResponse(Corpus corpus);
    List<CorpusResponse> convertFromCorpusListToResponse(List<Corpus> corpusList);
    Corpus convertFromCorpusRequestToEntity(CorpusRequest corpusRequest);


    @Mapping(target = "corpusId", qualifiedByName = "convertCorpusToId", source = "corpus")
    AuditoryResponse convertFromAuditoryToResponse(Auditory auditory);

    List<AuditoryResponse> convertFromAuditoryListToResponse(List<Auditory> users);

    @Named("convertCorpusToId")
    default Long convertCorpusToId(Corpus corpus) {
        if (corpus == null) {
            return null;
        }
        return corpus.getId();
    }

    @Mapping(target = "auditoryId", qualifiedByName = "convertAuditoryToId", source = "auditory")
    @Mapping(target = "userId", qualifiedByName = "convertUserToId", source = "user")
    EventResponse convertFromEventToResponse(Event event);

    List<EventResponse> convertFromEventListToResponse(List<Event> events);

    @Named("convertAuditoryToId")
    default Long convertAuditoryToId(Auditory auditory) {
        if (auditory == null) {
            return null;
        }
        return auditory.getId();
    }

    @Named("convertUserToId")
    default Long convertUserToId(User user) {
        if (user == null) {
            return null;
        }
        return user.getId();
    }


}