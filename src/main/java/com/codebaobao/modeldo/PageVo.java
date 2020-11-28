package com.codebaobao.modeldo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Builder
@Accessors
@AllArgsConstructor
public class PageVo {
    @NotNull(message = "pageNow 不能为空")
    private int pageNow;
    @NotNull(message = "pageSize 不能为空")
    private int pageSize;
}
