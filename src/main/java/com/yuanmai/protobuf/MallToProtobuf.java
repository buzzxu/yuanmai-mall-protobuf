package com.yuanmai.protobuf;

import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.yuanmai.mall.objects.Commodity;
import com.yuanmai.mall.objects.order.ItemSpec;
import com.yuanmai.mall.objects.order.ItemSpecVal;
import com.yuanmai.mall.objects.order.OrderDelivery;
import com.yuanmai.mall.objects.order.OrderItemMini;
import com.yuanmai.mall.objects.product.SpecType;
import com.yuanmai.mall.objects.product.Units;
import com.yuanmai.thirdparty.express.ExpressState;
import com.yuanmai.thirdparty.express.datas.Trace;
import com.yuanmai.thirdparty.express.datas.TracesInfo;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.List;

import static org.mapstruct.CollectionMappingStrategy.ADDER_PREFERRED;

@Mapper(collectionMappingStrategy = ADDER_PREFERRED
        ,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
        ,unmappedTargetPolicy = ReportingPolicy.IGNORE
        ,nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
        ,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MallToProtobuf {
    MallToProtobuf INSTANCE = Mappers.getMapper(MallToProtobuf.class);

    List<com.yuanmai.protobuf.mall.order.OrderDelivery> toDeliveres(List<OrderDelivery> objs);
    @Mappings({
            @Mapping(target = "itemIdsList",source = "itemIds"),
            @Mapping(target = "itemsList",source = "items")
    })
    com.yuanmai.protobuf.mall.order.OrderDelivery to(com.yuanmai.mall.objects.order.OrderDelivery obj);
    @Mappings({
            @Mapping(target = "specsList",source = "specs")
    })
    com.yuanmai.protobuf.mall.order.OrderItemMini to(OrderItemMini obj);

    @Mappings({
            @Mapping(target = "valsList",source = "vals")
    })
    com.yuanmai.protobuf.mall.order.ItemSpec to(ItemSpec obj);

    default com.yuanmai.protobuf.mall.order.ItemSpecVal to(ItemSpecVal obj){
        com.yuanmai.protobuf.mall.order.ItemSpecVal.Builder val = com.yuanmai.protobuf.mall.order.ItemSpecVal.newBuilder();
        val.setCode(obj.getCode());
        val.setName(obj.getName());
        if(obj.getValue() != null){
            try {
                val.setValue(Any.parseFrom(ByteString.copyFromUtf8(obj.getValue().toString())));
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        }
        return val.build();
    }

    @Mappings({
            @Mapping(target = "tracksList",source = "tracks")
    })
    com.yuanmai.protobuf.mall.express.TracesInfo to(TracesInfo obj);



    @ValueMappings({
            @ValueMapping(target = "UNRECOGNIZED",source = "UNKNOWN")
    })
    com.yuanmai.protobuf.mall.product.Units to(Units obj);

    @ValueMappings({
            @ValueMapping(target = "UNRECOGNIZED",source = "UNKNOWS")
    })
    com.yuanmai.protobuf.mall.express.ExpressState to(ExpressState obj);

    @ValueMappings({
            @ValueMapping(target = "UNRECOGNIZED",source = "UNKNOWN")
    })
    com.yuanmai.protobuf.mall.product.SpecType to(SpecType obj);


    @Mappings({
            @Mapping(target = "specsList",source = "specs")
    })
    com.yuanmai.protobuf.mall.Commodity to(Commodity obj);
}
