package com.depromeet.oversweet.member.service;

import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberPureService {



    public MemberEntity findBySocialId(String socialId) {
        return null;
    }

    public void save(MemberEntity member) {

    }
}
