package i.mooc.service;

import i.mooc.pojo.Carousel;

import java.util.List;

public interface CarouselService {
    
    /*
    * query all carousel
    * */
    public List<Carousel> queryAll(Integer isShow);
    
}
