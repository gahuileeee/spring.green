package kr.co.ch05.service;

import kr.co.ch05.dto.ChildDTO;
import kr.co.ch05.dto.MemberDTO;
import kr.co.ch05.dto.ParentDTO;
import kr.co.ch05.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private  final MemberMapper mapper;

    @Autowired
    public MemberService(MemberMapper mapper) {
        this.mapper = mapper;
    }

    public MemberDTO selectMember(String uid){
        return mapper.selectMember(uid);
    };
    public List<MemberDTO> selectMembers(){
        return mapper.selectMembers();
    };
    public void insertMember(MemberDTO memberDTO){
        mapper.insertMember(memberDTO);
    };
    public void deleteMember(String uid){
        mapper.deleteMember(uid);
    };
    public void updateMember(MemberDTO memberDTO){
        mapper.updateMember(memberDTO);
    };
    public List<MemberDTO>  selectMembersForSearch(String type, String value, String [] pos){
        return mapper. selectMembersForSearch(type, value, pos);
    };

    public List<ParentDTO> selectParentWithChild(){
        return  mapper.selectParentWithChild();
    };
}
