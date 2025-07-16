package com.sprint.mission.discodeit.main;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFMessageService;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        JCFUserService jcfUs = new JCFUserService();

        //[ ] 등록
        User user1 = jcfUs.create("user1","1234", UserStatus.ONLINE);
        User user2 = jcfUs.create("user2","1234",UserStatus.ONLINE);
        User user3 = jcfUs.create("user3","1234",UserStatus.OFFLINE);
        User user4 = jcfUs.create("user4","1234",UserStatus.IDLE);
        User user5 = jcfUs.create("person1","1234",UserStatus.DO_NOT_DISTURB);
        //[ ] 조회(단건, 다건)
        System.out.println("조회: 단건");
        List<User> users = jcfUs.find("user1");
//        for (User user : users) {
//            System.out.println(user.toString());
//        }
        users.forEach(System.out::println);

        System.out.println("조회: 다건");
        users.stream().sorted().forEach(System.out::println);
        System.out.println("조회: 전체");
        users = jcfUs.findAll();
        users.stream().sorted().forEach(System.out::println);

        //[ ] 수정
        if(jcfUs.update(user1.getId(),"user11","1234","1111",UserStatus.IDLE)){
            System.out.println("업데이트 성공!");
        } else System.out.println("업데이트 실패!");
        //[ ] 수정된 데이터 조회
        System.out.println("수정된 데이터 조회");
        users = jcfUs.find("user11");
        users.stream().sorted().forEach(System.out::println);

        //[ ] 삭제
        System.out.println("삭제");
        if(jcfUs.delete(user4.getId(),"1234")) {
            System.out.println("삭제 완료");
        } else System.out.println("삭제 실패");

        //[ ] 조회를 통해 삭제되었는지 확인
        System.out.println("삭제되었는지 확인");
        users = jcfUs.findAll();
        users.stream().sorted().forEach(System.out::println);


        // channel //////////////////////////////////////
        JCFChannelService jcfCn = new JCFChannelService();
        //생성
        Channel channel1 = jcfCn.create("channel1","test1","1234");
        Channel channel2 = jcfCn.create("channel2","test2","1234");
        Channel channel3 = jcfCn.create("channel3","test3","1234");
        //조회(단건)
        System.out.println("채널조회(단건)");
        List<Channel> channel = jcfCn.find("channel1");
//        for (Channel c : channel) {
//            System.out.println(c.toString());
//        }
        channel.stream().forEach(System.out::println);
        //조회(다건)
        System.out.println("채널조회(다건)");
        channel = jcfCn.find("channel");
        channel.stream().sorted().forEach(System.out::println);
        //조회(전체)
        System.out.println("채널조회(전체)");
        channel = jcfCn.findAll();
        channel.stream().sorted().forEach(System.out::println);
        //[ ] 수정
        System.out.println("수정");
        if(jcfCn.update(channel1.getId(), "channel11","1234","1111")){
            System.out.println("업데이트 성공!");
        } else System.out.println("업데이트 실패!");
        //[ ] 수정된 데이터 조회
        System.out.println("수정된 데이터 조회");
        channel = jcfCn.find("channel1");
        channel.stream().sorted().forEach(System.out::println);

        //삭제
        System.out.println("삭제");
        if(jcfCn.delete(channel3.getId(),"test3","1234")) {
            System.out.println("삭제 완료");
        } else System.out.println("삭제 실패");

        //[ ] 조회를 통해 삭제되었는지 확인
        System.out.println("삭제되었는지 확인");
        channel = jcfCn.findAll();
        channel.stream().sorted().forEach(System.out::println);

        //  /////////Message//////////////
        JCFMessageService jcfMs = new JCFMessageService();
        Message message1 = jcfMs.create(channel1.getId(),user1.getId(),"Hello");
        Message message2 = jcfMs.create(channel1.getId(),user1.getId(),"My Name is");
        Message message3 = jcfMs.create(channel1.getId(),user1.getId(),"ChanGyu");
        Message message4 = jcfMs.create(channel1.getId(),user2.getId(),"Hi");
        Message message5 = jcfMs.create(channel1.getId(),user3.getId(),"Hi");
        Message message6 = jcfMs.create(channel2.getId(),user1.getId(),"Hello2");
        Message message7 = jcfMs.create(channel2.getId(),user2.getId(),"Hi2");
        Message message8 = jcfMs.create(channel2.getId(),user3.getId(),"Hi2");
        Message message9 = jcfMs.create(channel3.getId(),user1.getId(),"Hello3");
        Message message10 = jcfMs.create(channel3.getId(),user2.getId(),"Hi3");

        //조회(단건)
        System.out.println("메세지 조회(단건)");
        Message msg = jcfMs.find(message1.getId());
        System.out.println(msg.toString());
        //조회(전체)
        System.out.println("메세지 조회(조회)");
        List<Message> msgs = new ArrayList<>();
        msgs = jcfMs.findAll();
        msgs.stream().sorted().forEach(System.out::println);
        //수정
        System.out.println("수정");
        if(jcfMs.update(message3.getId(), "IronMan")){
            System.out.println("업데이트 성공!");
        } else System.out.println("업데이트 실패!");
        //수정 후 조회
        System.out.println("수정 후 조회");
        msgs = jcfMs.findAll();
        msgs.stream().sorted().forEach(System.out::println);

        //삭제
        System.out.println("삭제");
        if(jcfMs.delete(message3.getId())) {
            System.out.println("삭제 완료");
        } else System.out.println("삭제 실패");
        //삭제 후 조회
        System.out.println("삭제 후 조회");
        msgs = jcfMs.findAll();
        msgs.stream().sorted().forEach(System.out::println);





    }
}
