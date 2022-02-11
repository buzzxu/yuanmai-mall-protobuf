package com.yuanmai.protobuf;

import org.mapstruct.*;

import static org.mapstruct.CollectionMappingStrategy.ADDER_PREFERRED;

@Mapper(collectionMappingStrategy = ADDER_PREFERRED
        ,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
        ,unmappedTargetPolicy = ReportingPolicy.IGNORE
        ,nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
        ,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MallMapStructs {
}