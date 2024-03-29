package com.yuanmai.protobuf;

import com.yuanmai.mall.objects.Commodity;
import com.yuanmai.mall.objects.order.ItemSpec;
import com.yuanmai.mall.objects.order.ItemSpecVal;
import com.yuanmai.mall.objects.order.OrderDelivery;
import com.yuanmai.mall.objects.order.OrderItemMini;
import com.yuanmai.mall.objects.product.SpecType;
import com.yuanmai.mall.objects.product.Units;
import com.yuanmai.thirdparty.express.ExpressState;
import com.yuanmai.thirdparty.express.datas.TracesInfo;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.mapstruct.CollectionMappingStrategy.ADDER_PREFERRED;

@Mapper(collectionMappingStrategy = ADDER_PREFERRED
        ,nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
        ,unmappedTargetPolicy = ReportingPolicy.IGNORE
        ,nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
        ,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProtobufToMall {
    ProtobufToMall INSTANCE = Mappers.getMapper(ProtobufToMall.class);

    List<OrderDelivery> toDeliveres(List<com.yuanmai.protobuf.mall.order.OrderDelivery> objs);
    @Mappings({
            @Mapping(target = "itemIds",source = "itemIdsList"),
            @Mapping(target = "items",source = "itemsList")
    })
    OrderDelivery to(com.yuanmai.protobuf.mall.order.OrderDelivery obj);

    @Mappings({
            @Mapping(target = "specs",source = "specsList")
    })
    OrderItemMini to(com.yuanmai.protobuf.mall.order.OrderItemMini obj);

    @Mappings({
            @Mapping(target = "vals",source = "valsList")
    })
    ItemSpec to(com.yuanmai.protobuf.mall.order.ItemSpec obj);

    default ItemSpecVal to(com.yuanmai.protobuf.mall.order.ItemSpecVal obj){
        ItemSpecVal val = new ItemSpecVal();
        val.setCode(obj.getCode());
        val.setName(obj.getName());
        if(obj.getValue() != null && obj.getValue().isInitialized()){
            val.setValue(obj.getValue().getValue().toStringUtf8());
        }
        return val;
    }
    @Mappings({
            @Mapping(target = "tracks",source = "tracksList")
    })
    TracesInfo to(com.yuanmai.protobuf.mall.express.TracesInfo obj);

    @ValueMappings({
            @ValueMapping(target = "UNKNOWN",source = "UNRECOGNIZED")
    })
    Units to(com.yuanmai.protobuf.mall.product.Units obj);

    @ValueMappings({
            @ValueMapping(target = "UNKNOWS",source = "UNRECOGNIZED")
    })
    ExpressState to(com.yuanmai.protobuf.mall.express.ExpressState obj);

    @ValueMappings({
            @ValueMapping(target = "UNKNOWN",source = "UNRECOGNIZED")
    })
    SpecType to(com.yuanmai.protobuf.mall.product.SpecType obj);

    @Mappings({
            @Mapping(target = "specs", source = "specsList")
    })
    Commodity to(com.yuanmai.protobuf.mall.Commodity obj);
}
