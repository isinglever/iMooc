package i.mooc.service;

import i.mooc.pojo.*;
import i.mooc.pojo.vo.CategoryVO;
import i.mooc.pojo.vo.CommentLevelCountsVO;
import i.mooc.pojo.vo.NewItemsVO;
import i.mooc.utils.PagedGridResult;

import java.util.List;

public interface ItemService {
    /**
     * 根据商品ID查询详情
     * @param itemId
     * @return
     */
    public Items queryItemById(String itemId);

    /**
     * 根据商品id查询商品图片列表
     * @param itemId
     * @return
     */
    public List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品id查询商品规格
     * @param itemId
     * @return
     */
    public List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品id查询商品参数
     * @param itemId
     * @return
     */
    public ItemsParam queryItemParam(String itemId);

    public CommentLevelCountsVO queryCommentCounts(String itemId);
    /**
     * 根据商品id查询商品的评价（分页）
     * @param itemId
     * @param level
     * @return
     */
    public PagedGridResult queryPagedComments(String itemId, Integer level,
                                              Integer page, Integer pageSize);

    public PagedGridResult searchItems(String keywords, String sort,
                                              Integer page, Integer pageSize);

    public PagedGridResult searchItems(Integer catId, String sort,
                                      Integer page, Integer pageSize);

}
