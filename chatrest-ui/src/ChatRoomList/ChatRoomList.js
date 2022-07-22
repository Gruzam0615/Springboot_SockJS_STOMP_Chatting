import { useEffect, useState } from "react";
import { over } from "stompjs";
import SockJS from "sockjs-client";

const ChatRoomList = () => {

    const [ chatRoomsList, setChatRoomsList ] = useState([]);

    useEffect(() => {
    }, []);

    const getChatRoomsList = () => {
        const URL = "/chat/room";
        const OPTIONS = {
            method: "GET",
            redirect: "follow"
        }
        return fetch(URL, OPTIONS)
            .then(response => response.json())
            .then(result => console.log(result))
            .catch(error => console.log('error', error));
    }

    return(
        <div className="ChatRoomList">
            <h3>채팅방 목록</h3>
            <button onClick={getChatRoomsList}>목록 불러오기</button>
        </div>
    );
}
export default ChatRoomList;