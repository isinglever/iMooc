package i.mooc.controller;

import i.mooc.enums.YesOrNo;
import i.mooc.pojo.Carousel;
import i.mooc.pojo.Category;
import i.mooc.pojo.vo.CategoryVO;
import i.mooc.pojo.vo.NewItemsVO;
import i.mooc.service.CarouselService;
import i.mooc.service.CategoryService;
import i.mooc.utils.IMOOCJSONResult;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

//@Controller
@RestController
@RequestMapping("index")
public class IndexController {
    @Autowired
    private CarouselService carouselService;
     
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/carousel")
    public IMOOCJSONResult carousel() {
        List<Carousel> list = carouselService.queryAll(YesOrNo.YES.type);
        return IMOOCJSONResult.ok(list);
    }
    
    /*
    * query the main category when the first load
    * if mouse move to a main category, load its child category. if child category has already existed, do nothing.
    * */


    @GetMapping("/cats")
    public IMOOCJSONResult cats() {
        List<Category> list = categoryService.queryAllRootLevelCat();
        return IMOOCJSONResult.ok(list);
    }


    @GetMapping("/subCat/{rootCatId}")
    public IMOOCJSONResult subCat(
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg("分类不存在");
        }

        List<CategoryVO> list = categoryService.getSubCatList(rootCatId);
        return IMOOCJSONResult.ok(list);
    }
    
    @GetMapping("/sixNewItems/{rootCatId}")
    public IMOOCJSONResult sixNewItems(
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return IMOOCJSONResult.errorMsg("分类不存在");
        }

        List<NewItemsVO> list = categoryService.getSixNewItemsLazy(rootCatId);
        return IMOOCJSONResult.ok(list);
    }
}
