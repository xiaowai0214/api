package com.egova.api.condition;

import com.egova.api.enums.DataType;
import com.egova.model.annotation.Display;
import com.flagwind.persistent.annotation.Condition;
import com.flagwind.persistent.annotation.ConditionOperator;
import com.flagwind.persistent.model.ClauseOperator;
import lombok.Data;

import java.io.Serializable;

/**
 * created by huangkang
 */
@Data
@Condition
@Display("api结果转换字段映像")
public class FieldMappingCondition implements Serializable {

    @Display("主键")
    @ConditionOperator(name = "id", operator = ClauseOperator.Equal)
    private String id;

    @Display("")
    @ConditionOperator(name = "apiId", operator = ClauseOperator.Equal)
    private String apiId;

    @Display("参数路劲")
    @ConditionOperator(name = "paramPath", operator = ClauseOperator.Equal)
    private String paramPath;

    @Display("新参数名称")
    @ConditionOperator(name = "name", operator = ClauseOperator.Equal)
    private String name;

    @Display("参数值")
    @ConditionOperator(name = "valueContent", operator = ClauseOperator.Equal)
    private String valueContent;

    @Display("值类型")
    @ConditionOperator(name = "valueType", operator = ClauseOperator.Equal)
    private DataType valueType;

}
