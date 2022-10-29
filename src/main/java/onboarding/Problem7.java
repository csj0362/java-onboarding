package onboarding;

import java.util.*;

public class Problem7 {
    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        Map<String, List<String>> map = friends(friends);
        List<String> userFriends = extractUserFriendsList(friends, user);
        List<String> allUser = extractAllUserList(friends);
        List<String> userNotFriends = extractUserNotFriendsList(userFriends, user, allUser);

        Map<String, List<String>> userInFriendsLinkedList = userInFriendsLinkedList(map, userNotFriends);

        List<String> asd = createListFriendsOfFriends(userInFriendsLinkedList, userFriends);
        countDuplicateFriends(asd,user);

        return userFriends;
    }

    // 모든 friends 리스트 생성
    public static List<String> extractAllUserList(List<List<String>> friends) {
        List<String> allUserList = new ArrayList<>();

        for (int i = 0; i < friends.size(); i++) {
            if (!allUserList.contains(friends.get(i).get(0))) {
                allUserList.add(friends.get(i).get(0));
            }
        }

        for (int i = 0; i < friends.size(); i++) {
            if (!allUserList.contains(friends.get(i).get(1))) {
                allUserList.add(friends.get(i).get(1));
            }
        }

        System.out.println("alluserList" + allUserList);
        return allUserList;
    }

    // user의 친구들 리스트 생성
    public static List<String> extractUserFriendsList(List<List<String>> friends,
                                                      String user) {
        List<String> userFriendsList = new ArrayList<>();

        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).contains(user)) {
                userFriendsList.add(friends.get(i).get(0));
                userFriendsList.add(friends.get(i).get(1));
            }
        }
        removeUserFromUserFriendsList(userFriendsList, user);

        System.out.println("userFriendsList" + userFriendsList);
        return userFriendsList;
    }

    // user와 친구가 아닌 친구들 리스트 생성
    public static List<String> extractUserNotFriendsList(List<String> userFriends, String user, List<String> allUser) {
        List<String> userNotFriendsList = allUser;

        for (int i = 0; i < userFriends.size(); i++) {
            userNotFriendsList.remove(userFriends.get(i));
        }
        userNotFriendsList.remove(user);

        System.out.println("userNotFriendList" + allUser);
        return userNotFriendsList;
    }

    // userFriendList에서 user이름 제거
    public static List<String> removeUserFromUserFriendsList(
            List<String> userFriendList, String user) {

        while (userFriendList.remove(user)) {
        }

        return userFriendList;
    }

    // 리스트로 나와있는것을 왼쪽 친구 이름 기준으로 맵에 등록
    public static Map<String, List<String>> friends(List<List<String>> friends) {
        Map<String, List<String>> friendsLinkedList = new TreeMap<>();
        List<String> belongFriendsList = new ArrayList<>();

        for (int i = 0; i < friends.size(); i++) {
            if (friendsLinkedList.containsKey(friends.get(i).get(0))) {
                belongFriendsList.add(friends.get(i).get(1));
                friendsLinkedList.put(friends.get(i).get(0), belongFriendsList);
            }
            if (!friendsLinkedList.containsKey(friends.get(i).get(0))) {
                belongFriendsList = new ArrayList<>();
                belongFriendsList.add(friends.get(i).get(1));
                friendsLinkedList.put(friends.get(i).get(0), belongFriendsList);
            }
        }

        System.out.println(friendsLinkedList);
        return friendsLinkedList;
    }

    // 왼쪽 친구이름으로 정렬한 맵에서 user와 친구가 아닌것을 제거
    public static Map<String, List<String>> userInFriendsLinkedList(
            Map<String, List<String>> friendsLinkedList,
            List<String> userNotFriendList) {
        for (int i = 0; i < friendsLinkedList.size(); i++) {
            friendsLinkedList.remove(userNotFriendList.get(i));
        }

        System.out.println("연결된 리스트의 값에서 user가 없는 부분 제거" + friendsLinkedList);


        return friendsLinkedList;
    }

    // 내 친구들의 모든 친구들을 리스트에 추가
    public static List<String> createListFriendsOfFriends(Map<String, List<String>> friendsLinkedList, List<String> userFriendsList) {
        List<String> allFriendOfFriendsLine = new ArrayList<>();

        for (int i = 0; i < friendsLinkedList.size(); i++) {
            for (int j = 0; j < friendsLinkedList.get(userFriendsList.get(i)).size(); j++) {
                allFriendOfFriendsLine.add(friendsLinkedList.get(userFriendsList.get(i)).get(j));
            }
        }

        System.out.println(allFriendOfFriendsLine);
        return allFriendOfFriendsLine;
    }

    // 중복되는 이름 10점씩 추가
    public static Map<String, Integer> countDuplicateFriends(List<String> allFriendOfFriendsLine, String user) {
        Map<String, Integer> countFriends = new HashMap<>();

        removeUserFromUserFriendsList(allFriendOfFriendsLine, user);

        for (int i = 0; i < allFriendOfFriendsLine.size(); i++) {
            if (countFriends.containsKey(allFriendOfFriendsLine.get(i))) {
                countFriends.put(allFriendOfFriendsLine.get(i), countFriends.get(allFriendOfFriendsLine.get(i))+10);
            }
            if (!countFriends.containsKey(allFriendOfFriendsLine.get(i))) {
                countFriends.put(allFriendOfFriendsLine.get(i), 10);
            }
        }

        System.out.println(countFriends);
        return countFriends;
    }
}