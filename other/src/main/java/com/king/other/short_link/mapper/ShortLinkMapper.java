package com.king.other.short_link.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.king.other.short_link.bean.ShortLink;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShortLinkMapper extends BaseMapper<ShortLink> {
}
