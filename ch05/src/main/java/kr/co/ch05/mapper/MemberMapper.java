package kr.co.ch05.mapper;

import kr.co.ch05.dto.ChildDTO;
import kr.co.ch05.dto.MemberDTO;
import kr.co.ch05.dto.ParentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {

    public MemberDTO selectMember(String uid);
    public List<MemberDTO> selectMembers();
    public List<MemberDTO> selectMembersForSearch
            (@Param("type") String type, @Param("value") String value, @Param("pos") String [] pos);
    public void insertMember(MemberDTO memberDTO);
    public void deleteMember(String uid);
    public void updateMember(MemberDTO memberDTO);
    public List<ParentDTO> selectParentWithChild();
}
