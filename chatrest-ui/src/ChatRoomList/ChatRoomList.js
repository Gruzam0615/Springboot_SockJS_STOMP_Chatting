import { useEffect, useRef, useState } from "react";
import { over } from "stompjs";
import SockJS from "sockjs-client";

const ChatRoomList = () => {

    const [ chatRoomsList, setChatRoomsList ] = useState([]);
    const [ roomNameValue, setRoomNameValue ] = useState("");

    useEffect(() => {
        getChatRoomsList();
    });

    const ChangeRoomName = (e) => {
        setRoomNameValue(e.target.value);
    }

    // 채팅방을 생성하는 함수
    const createChatRoomList = () => {
        const URL = `/chat/room?roomName=${roomNameValue}&usersIdx=1`;
        const OPTIONS = {
            method: "POST",
            redirect: "follow"
        }
        fetch(URL, OPTIONS)
            .then((response) => {
                // console.log(response);
                setRoomNameValue("");
            })
    }

    // 생성된 채팅방 목록을 받는 함수
    const getChatRoomsList = () => {
        const URL = "/chat/room";
        const OPTIONS = {
            method: "GET",
            redirect: "follow"
        }

        fetch(URL, OPTIONS)
            .then(response => response.json())
            .then(result => {
                setChatRoomsList(result.responseItem);
            })
            .catch(error => console.log('error', error));
    }

    return(
        <div className="ChatRoomList">
            <h3>채팅방 목록</h3>
            <div className="chatRoomsListDiv">
                <ul className="ChatRoomList">
                {
                    chatRoomsList.length <= 0 ?
                    <li>채팅방이 없습니다.</li>
                    :
                    chatRoomsList.map((item, index) => 
                    <li key={index}>
                        <a href={`/chat/room/enter/${item.roomId}?usersName=PMJ`}>
                        {item.roomName}
                        </a>                        
                    </li>
                    )
                }
                </ul>
            </div>           
            {/* <button onClick={getChatRoomsList}>목록 불러오기</button> */}
            <br/>
            <div>
                <label htmlFor="roomName">채팅방 제목</label>
                <input type="text" className="" name="roomName" onChange={ChangeRoomName} value={roomNameValue}></input>
                <input type="button" value="만들기" onClick={createChatRoomList}/>
            </div>
        </div>
    );
}
export default ChatRoomList;