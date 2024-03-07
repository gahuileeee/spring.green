package kr.co.ch05.mapper;

import kr.co.ch05.dto.LunchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LunchMapper {
    public void insertLunch(LunchDTO lunchDTO);
    public void updateLunch(LunchDTO lunchDTO);
    public void deleteLunch(String seq);
    public LunchDTO selectLunch(String seq);
    public List<LunchDTO> selectLunchs();
    public LunchDTO selectLunchOne();
}
