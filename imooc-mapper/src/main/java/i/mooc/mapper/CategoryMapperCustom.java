package i.mooc.mapper;

import i.mooc.my.mapper.MyMapper;
import i.mooc.pojo.Category;
import i.mooc.pojo.vo.CategoryVO;
import i.mooc.pojo.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.javassist.expr.NewArray;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom {
    public List<CategoryVO> getSubCatList(Integer rootCatId);
    
    public List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);
}