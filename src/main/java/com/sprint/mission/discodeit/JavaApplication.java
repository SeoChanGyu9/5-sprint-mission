package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.*;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.file.FileChannelRepository;
import com.sprint.mission.discodeit.repository.file.FileMessageRepository;
import com.sprint.mission.discodeit.repository.file.FileUserRepository;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.file.FileChannelService;
import com.sprint.mission.discodeit.service.file.FileMessageService;
import com.sprint.mission.discodeit.service.file.FileUserService;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFMessageService;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

import java.util.ArrayList;
import java.util.List;

public class JavaApplication {
    public static void main(String[] args) {


//        jcfTest();
//        fileUserTest();
//        fileChannelTest();
        fileMessageTest();
    }

    public static void fileMessageTest() {
        MessageRepository fileMessageRepository = new FileMessageRepository();
        MessageService fileMessageService = new FileMessageService(fileMessageRepository);

        FileChannelRepository fileChannelRepository = new FileChannelRepository();
        ChannelService fileChannelService = new FileChannelService(fileChannelRepository);
        FileUserRepository fileUserRepository = new FileUserRepository();
        UserService fileUserService = new FileUserService(fileUserRepository);

        Channel channel1 = fileChannelService.create("channel1", "user1", "1234", ChannelStatus.PUBLIC);
        Channel channel2 = fileChannelService.create("channel2", "user2", "1234", ChannelStatus.PUBLIC);
        Channel channel3 = fileChannelService.create("channel3", "user3", "1234", ChannelStatus.PUBLIC);

        User user1 = fileUserService.create("Hong", "1234", UserStatus.ONLINE);
        User user2 = fileUserService.create("Kim", "1234", UserStatus.ONLINE);
        User user3 = fileUserService.create("Kang", "1234", UserStatus.ONLINE);

        //Message 생성
        Message message1 = fileMessageService.create(channel1.getId(), user1.getId(), "Hello");
        Message message2 = fileMessageService.create(channel1.getId(), user2.getId(), "Hi");
        Message message3 = fileMessageService.create(channel1.getId(), user1.getId(), "World");
        Message message4 = fileMessageService.create(channel1.getId(), user2.getId(), "Yes");
        Message message5 = fileMessageService.create(channel1.getId(), user1.getId(), "GOOD");
        Message message6 = fileMessageService.create(channel2.getId(), user1.getId(), "c2");
        Message message7 = fileMessageService.create(channel3.getId(), user1.getId(), "c3");

        //조회
        System.out.println("조회: " + fileMessageService.find(message1.getId()));
        //조회 전체
        System.out.println("조회 전체");
        List<Message> messageList = fileMessageService.findAll();
        messageList.stream().forEach(System.out::println);

        //삭제
        System.out.println("삭제");
        fileMessageService.delete(message1.getId());
        //삭제 후 전체 조회
        System.out.println("삭제 후 전체 조회");
        fileMessageService.findAll().stream().forEach(System.out::println);
        //업데이트
        System.out.println("업데이트");
        fileMessageService.update(message2.getId(), "WOW");
        //업뎃후 조회
        System.out.println("업데이트 후 조회: " + fileMessageService.find(message2.getId()).toString());
        //사용자가 존재하는가
        System.out.println("업데이트한 message2 가 존재하는가?: " + fileMessageService.existsById(channel2.getId()));
        //파일개수체크
        System.out.println("메세지 수: " + fileMessageService.count());
    }

    public static void fileChannelTest() {
        ChannelRepository fileChannelRepository = new FileChannelRepository();
        ChannelService fileChannelService = new FileChannelService(fileChannelRepository);

        //Channel 생성
        Channel channel1 = fileChannelService.create("channel1", "user1", "1234", ChannelStatus.PUBLIC);
        Channel channel2 = fileChannelService.create("channel2", "user2", "1234", ChannelStatus.PUBLIC);
        Channel channel3 = fileChannelService.create("channel3", "user3", "1234", ChannelStatus.PUBLIC);

        //조회
        System.out.println("조회: " + fileChannelService.find(channel1.getId()));
        //조회 전체
        System.out.println("조회 전체");
        List<Channel> channelList = fileChannelService.findAll();
        channelList.stream().forEach(System.out::println);

        //삭제
        System.out.println("삭제");
        fileChannelService.delete(channel1.getId(), "user1", channel1.getPassword());
        //삭제 후 전체 조회
        System.out.println("삭제 후 전체 조회");
        fileChannelService.findAll().stream().forEach(System.out::println);
        //업데이트
        System.out.println("업데이트");
        fileChannelService.update(channel2.getId(), "channel99", "1234", "1111", ChannelStatus.PRIVATE);
        //업뎃후 조회
        System.out.println("업데이트 후 조회: " + fileChannelService.find(channel2.getId()).toString());
        //사용자가 존재하는가
        System.out.println("업데이트한 channel99 가 존재하는가?: " + fileChannelService.existsById(channel2.getId()));
        //파일개수체크
        System.out.println("채널 수: " + fileChannelService.count());

    }

    public static void fileUserTest() {
        UserRepository fileUserRepository = new FileUserRepository();
        UserService fileUserService = new FileUserService(fileUserRepository);

        //User 생성
        User user1 = fileUserService.create("Hong", "1234", UserStatus.ONLINE);
        User user2 = fileUserService.create("Kim", "1234", UserStatus.ONLINE);
        User user3 = fileUserService.create("Kang", "1234", UserStatus.ONLINE);

        //조회
        System.out.println("조회: " + fileUserService.find(user1.getId()).toString());
        //조회 전체
        System.out.println("조회 전체");
        List<User> userList = fileUserService.findAll();
        userList.stream().forEach(System.out::println);

        //삭제
        System.out.println("삭제");
        fileUserService.delete(user1.getId(), user1.getPassword());
        //삭제 후 전체 조회
        System.out.println("삭제 후 전체 조회");
        fileUserService.findAll().stream().forEach(System.out::println);
        //업데이트
        System.out.println("업데이트");
        fileUserService.update(user2.getId(), "Seo", "1234", "1111", UserStatus.OFFLINE);
        //업뎃후 조회
        System.out.println("업데이트 후 조회: " + fileUserService.find(user2.getId()).toString());
        //사용자가 존재하는가
        System.out.println("업데이트한 Seo 유저가 존재하는가?: " + fileUserService.existsById(user2.getId()));
        //파일개수체크
        System.out.println("사용자 수: " + fileUserService.count());
    }


    public static void jcfTest() {
        //        UserService jcfUs = new JCFUserService();
        UserService jcfUs = JCFUserService.getInstance();
        //[ ] 등록
        User user1 = jcfUs.create("user1", "1234", UserStatus.ONLINE);
        User user2 = jcfUs.create("user2", "1234", UserStatus.ONLINE);
        User user3 = jcfUs.create("user3", "1234", UserStatus.OFFLINE);
        User user4 = jcfUs.create("user4", "1234", UserStatus.IDLE);
        User user5 = jcfUs.create("person1", "1234", UserStatus.DO_NOT_DISTURB);
        //[ ] 조회(단건, 다건)
        System.out.println("조회: 단건");
        User findUser = jcfUs.find(user1.getId());
        System.out.println(findUser.toString());

        System.out.println("조회: 전체");
        List<User> users = jcfUs.findAll();
        users.stream().sorted().forEach(System.out::println);

        //[ ] 수정
        if (jcfUs.update(user1.getId(), "user11", "1234", "1111", UserStatus.IDLE)) {
            System.out.println("업데이트 성공!");
        } else System.out.println("업데이트 실패!");
        //[ ] 수정된 데이터 조회
        System.out.println("수정된 데이터 조회");
        findUser = jcfUs.find(user1.getId());
        users.stream().sorted().forEach(System.out::println);

        //[ ] 삭제
        System.out.println("삭제");
        if (jcfUs.delete(user4.getId(), "1234")) {
            System.out.println("삭제 완료");
        } else System.out.println("삭제 실패");

        //[ ] 조회를 통해 삭제되었는지 확인
        System.out.println("삭제되었는지 확인");
        users = jcfUs.findAll();
        users.stream().sorted().forEach(System.out::println);


        // channel //////////////////////////////////////
        ChannelService jcfCn = JCFChannelService.getInstance();
        //생성
        Channel channel1 = jcfCn.create("channel1", "test1", "1234", ChannelStatus.PUBLIC);
        Channel channel2 = jcfCn.create("channel2", "test2", "1234", ChannelStatus.PUBLIC);
        Channel channel3 = jcfCn.create("channel3", "test3", "1234", ChannelStatus.PUBLIC);
        //조회(단건)
        System.out.println("채널조회(단건)");
        Channel channel = jcfCn.find(channel1.getId());
//        for (Channel c : channel) {
//            System.out.println(c.toString());
//        }
        //channel.stream().forEach(System.out::println);
        System.out.println(channel.toString());
        //조회(다건)
//        System.out.println("채널조회(다건)");
//        channel = jcfCn.find("channel");
//        channel.stream().sorted().forEach(System.out::println);
        //조회(전체)
        System.out.println("채널조회(전체)");
        List<Channel> channel11 = jcfCn.findAll();
        channel11.stream().sorted().forEach(System.out::println);
        //[ ] 수정
        System.out.println("수정");
        if (jcfCn.update(channel1.getId(), "channel11", "1234", "1111", ChannelStatus.PRIVATE)) {
            System.out.println("업데이트 성공!");
        } else System.out.println("업데이트 실패!");
        //[ ] 수정된 데이터 조회
        System.out.println("수정된 데이터 조회");
        channel = jcfCn.find(channel1.getId());
        System.out.println(channel.toString());

        //삭제
        System.out.println("삭제");
        if (jcfCn.delete(channel3.getId(), "test3", "1234")) {
            System.out.println("삭제 완료");
        } else System.out.println("삭제 실패");

        //[ ] 조회를 통해 삭제되었는지 확인
        System.out.println("삭제되었는지 확인");
        channel11 = jcfCn.findAll();
        channel11.stream().sorted().forEach(System.out::println);

        //  /////////Message//////////////
        MessageService jcfMs = JCFMessageService.getInstance();
        Message message1 = jcfMs.create(channel1.getId(), user1.getId(), "Hello");
        Message message2 = jcfMs.create(channel1.getId(), user1.getId(), "My Name is");
        Message message3 = jcfMs.create(channel1.getId(), user1.getId(), "ChanGyu");
        Message message4 = jcfMs.create(channel1.getId(), user2.getId(), "Hi");
        Message message5 = jcfMs.create(channel1.getId(), user3.getId(), "Hi");
        Message message6 = jcfMs.create(channel2.getId(), user1.getId(), "Hello2");
        Message message7 = jcfMs.create(channel2.getId(), user2.getId(), "Hi2");
        Message message8 = jcfMs.create(channel2.getId(), user3.getId(), "Hi2");

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
        if (jcfMs.update(message3.getId(), "IronMan")) {
            System.out.println("업데이트 성공!");
        } else System.out.println("업데이트 실패!");
        //수정 후 조회
        System.out.println("수정 후 조회");
        msgs = jcfMs.findAll();
        msgs.stream().sorted().forEach(System.out::println);

        //삭제
        System.out.println("삭제");
        if (jcfMs.delete(message3.getId())) {
            System.out.println("삭제 완료");
        } else System.out.println("삭제 실패");
        //삭제 후 조회
        System.out.println("삭제 후 조회");
        msgs = jcfMs.findAll();
        msgs.stream().sorted().forEach(System.out::println);
    }
}
