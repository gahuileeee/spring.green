package kr.co.ch05.service;

import kr.co.ch05.dto.LunchDTO;
import kr.co.ch05.mapper.LunchMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LunchService {
    private final LunchMapper mapper;

    public LunchService(LunchMapper mapper) {
        this.mapper = mapper;
    }

    public void insertLunch(LunchDTO lunchDTO){
        mapper.insertLunch(lunchDTO);
    };
    public void updateLunch(LunchDTO lunchDTO){
        mapper.updateLunch(lunchDTO);
    };
    public void deleteLunch(String seq){
        mapper.deleteLunch(seq);
    };
    public LunchDTO selectLunch(String seq){
        return mapper.selectLunch(seq);
    };
    public List<LunchDTO> selectLunchs(){
        return  mapper.selectLunchs();
    };

    public LunchDTO selectLunchOne(){
        return  mapper.selectLunchOne();
    };
}
