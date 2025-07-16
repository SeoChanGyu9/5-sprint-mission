package com.sprint.mission.discodeit.main;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFMessageService;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        JCFUserService jcfUs = new JCFUserService();

        //[ ] 등록
        jcfUs.create("test1", "user1","1234", UserStatus.ONLINE);
        jcfUs.create("test2", "user2","1234",UserStatus.ONLINE);
        jcfUs.create("test3", "user3","1234",UserStatus.OFFLINE);
        jcfUs.create("test4", "user4","1234",UserStatus.IDLE);
        jcfUs.create("test5", "person1","1234",UserStatus.DO_NOT_DISTURB);
        //[ ] 조회(단건, 다건)
        System.out.println("조회: 단건");
        List<User> users = jcfUs.find("user1");
//        for (User user : users) {
//            System.out.println(user.toString());
//        }
        users.forEach(System.out::println);

        System.out.println("조회: 다건");
        users = jcfUs.find("user");
//        for (User user : users) {
//            System.out.println(user.toString());
//        }
        users.stream().sorted().forEach(System.out::println);
        System.out.println("조회: 전체");
        users = jcfUs.findAll();
//        for (User user : users) {
//            System.out.println(user.toString());
//        }
        users.stream().sorted().forEach(System.out::println);

        //[ ] 수정
        if(jcfUs.update("test1","user11","1234","1111",UserStatus.IDLE)){
            System.out.println("업데이트 성공!");
        } else System.out.println("업데이트 실패!");
        //[ ] 수정된 데이터 조회
        System.out.println("수정된 데이터 조회");
        users = jcfUs.find("user11");
//        for (User user : users) {
//            System.out.println(user.toString());
//        }
        users.stream().sorted().forEach(System.out::println);

        //[ ] 삭제
        System.out.println("삭제");
        if(jcfUs.delete("test4","1234")) {
            System.out.println("삭제 완료");
        } else System.out.println("삭제 실패");

        //[ ] 조회를 통해 삭제되었는지 확인
        System.out.println("삭제되었는지 확인");
        users = jcfUs.findAll();
//        for (User user : users) {
//            System.out.println(user.toString());
//        }
        users.stream().sorted().forEach(System.out::println);


        // channel //////////////////////////////////////
        JCFChannelService jcfCn = new JCFChannelService();
        //생성
        jcfCn.create("channel1","test1","1234");
        jcfCn.create("channel2","test2","1234");
        jcfCn.create("channel3","test3","1234");
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
//        for (Channel c : channel) {
//            System.out.println(c.toString());
//        }
        channel.stream().sorted().forEach(System.out::println);
        //조회(전체)
        System.out.println("채널조회(전체)");
        channel = jcfCn.findAll();
//        for (Channel c : channel) {
//            System.out.println(c.toString());
//        }
        channel.stream().sorted().forEach(System.out::println);
        //[ ] 수정
        System.out.println("수정");
        if(jcfCn.update("channel1","1234","1111")){
            System.out.println("업데이트 성공!");
        } else System.out.println("업데이트 실패!");
        //[ ] 수정된 데이터 조회
        System.out.println("수정된 데이터 조회");
        channel = jcfCn.find("channel1");
//        for (Channel c : channel) {
//            System.out.println(c.toString());
//        }
        channel.stream().sorted().forEach(System.out::println);

        //삭제
        System.out.println("삭제");
        if(jcfCn.delete("channel3","test3","1234")) {
            System.out.println("삭제 완료");
        } else System.out.println("삭제 실패");

        //[ ] 조회를 통해 삭제되었는지 확인
        System.out.println("삭제되었는지 확인");
        channel = jcfCn.findAll();
//        for (Channel c : channel) {
//            System.out.println(c.toString());
//        }
        channel.stream().sorted().forEach(System.out::println);

        //  /////////Message//////////////
        JCFMessageService jcfMs = new JCFMessageService();
        jcfMs.create("channel1","test1","Hello");
        jcfMs.create("channel1","test1","My Name is");
        jcfMs.create("channel1","test1","ChanGyu");
        jcfMs.create("channel1","test2","Hi");
        jcfMs.create("channel1","test3","Hi");
        jcfMs.create("channel2","test1","Hello2");
        jcfMs.create("channel2","test2","Hi2");
        jcfMs.create("channel2","test3","Hi2");
        jcfMs.create("channel3","test1","Hello3");
        jcfMs.create("channel3","test2","Hi3");

        //조회(단건,다건)
        UUID fixMsg = null;
        System.out.println("메세지 조회(다건)");
        List<Message> msg = jcfMs.find("channel1","test1");
        fixMsg = msg.get(2).getId(); //3번째 메세지 수정할 예정
//        for(Message m : msg){
//            System.out.println(m.toString());
//            fixMsg = m.getId(); //3번째 메세지 수정할 예정
//        }
        msg.stream().sorted().forEach(System.out::println);
        //조회(전체)
        System.out.println("메세지 조회(조회)");
        msg = jcfMs.findAll();
//        for(Message m : msg){
//            System.out.println(m.toString());
//        }
        msg.stream().sorted().forEach(System.out::println);
        //수정
        System.out.println("수정");
        if(jcfMs.update(fixMsg, "IronMan")){
            System.out.println("업데이트 성공!");
        } else System.out.println("업데이트 실패!");
        //수정 후 조회
        System.out.println("수정 후 조회");
        msg = jcfMs.findAll();
//        for(Message m : msg){
//            System.out.println(m.toString());
//        }
        msg.stream().sorted().forEach(System.out::println);

        //삭제
        System.out.println("삭제");
        if(jcfMs.delete(fixMsg)) {
            System.out.println("삭제 완료");
        } else System.out.println("삭제 실패");
        //삭제 후 조회
        System.out.println("삭제 후 조회");
        msg = jcfMs.findAll();
//        for(Message m : msg){
//            System.out.println(m.toString());
//        }
        msg.stream().sorted().forEach(System.out::println);





    }
}
