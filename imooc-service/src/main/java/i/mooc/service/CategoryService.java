package i.mooc.service;

import i.mooc.pojo.Category;
import i.mooc.pojo.vo.CategoryVO;
import i.mooc.pojo.vo.NewItemsVO;

import java.util.List;

public interface CategoryService {
    public List<Category> queryAllRootLevelCat();
    
    /**
     * 根据一级分类id查询子分类信息
     * @param rootCatId
     * @return
     */
    public List<CategoryVO> getSubCatList(Integer rootCatId);
    
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}
