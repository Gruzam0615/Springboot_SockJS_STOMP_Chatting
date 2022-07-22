import { useEffect, useRef, useState } from "react";
import { over } from "stompjs";
import SockJS from "sockjs-client";

const ChatRoomList = () => {

    const [ chatRoomsList, setChatRoomsList ] = useState([]);
    const [ roomNameValue, setRoomNameValue ] = useState("");
    const roomName = useRef();

    useEffect(() => {
    }, []);

    const createChatRoomList = () => {
        const URL = `/chat/room?roomName=${roomName}&usersIdx=1`;
        const OPTIONS = {
            method: "POST",
            redirect: "follow"
        }

        fetch(URL, OPTIONS)
    }

    const getChatRoomsList = () => {
        const URL = "/chat/room";
        const OPTIONS = {
            method: "GET",
            redirect: "follow"
        }

        fetch(URL, OPTIONS)
            .then(response => response.json())
            .then(result => { 
                console.log(result)
            })
            .catch(error => console.log('error', error));
    }

    return(
        <div className="ChatRoomList">
            <h3>채팅방 목록</h3>
            <button onClick={getChatRoomsList}>목록 불러오기</button>
            <br/>
            <div>
                <label htmlFor="roomName">채팅방 제목</label>
                <input type="text" className="" name="roomName" onChange={CustomInputChange} value={roomNameValue}></input>
                <input type="button" value="만들기" onClick={createChatRoomList}/>
            </div>
        </div>
    );
}
export default ChatRoomList;