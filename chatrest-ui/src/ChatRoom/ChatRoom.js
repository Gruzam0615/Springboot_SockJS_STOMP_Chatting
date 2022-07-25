import { useEffect, useState } from "react";
import { useParams, useSearchParams } from "react-router-dom";
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import ChatMessage from "../ChatMessage/ChatMessage";

const ChatRoom = () => {

    // url parameter parsing
    let parseParam = useParams();
    let roomId = parseParam.roomId;
    // ? 이후의 parameter를 parsing
    let [ searchParams, setSearchParams ] = useSearchParams();
    let usersName = searchParams.get("usersName");

    const [ chatRoom, setChatRoom ] = useState({
        chatRoomIdx: null,
        roomId: "",
        roomName: "",
        usersIdx: null,
        companysIdx: null,
        roomLogMeta: "",
        roomCreatedDate: ""
    });

    const [ sendMessage, setSendMessage ] = useState("");
    const [ messages, setMessages ] = useState([]);

    const sendMessageChange = (e) => {
        setSendMessage(e.target.value);
    }

    let sock = new SockJS("http://localhost:8080/ws/chat");
    let ws = Stomp.over(sock);
    let reconnect = 0;

    const findRoom = () => {
        const RequestURL = `/chat/room/${roomId}`;
        const options = {
            method: "GET"
        }
        fetch(RequestURL, options)
            .then((response) => response.json())
            .then((result) => {
                // console.log("## RESULT ##");
                console.log(result);
            })
    }

    const sendMessageAction = () => {
        // console.log(sendMessage);
        ws.send("/app/chat/message", {}, JSON.stringify({
            status: "TALK",
            roomId: chatRoom.roomId,
            sender: "TEMP",
            message: sendMessage,
            sendDate: "NULL"
        }))
        setSendMessage("");
    }
    
    const receiveMessage = (e) => {
        messages.unshift({
            "status": e.status,
            "sender": e.status=="ENTER"?"[알림]":e.sender,
            "message": e.message
        })
    }

    const connect = () => {
        ws.connect({}, (frame) => {
            ws.subscribe("/topic/chat/room"+chatRoom.roomId, (msg) => {
                let receving = JSON.parse(msg.body);
                receiveMessage(receving);
            });
            ws.send("/app/chat/message", {}, JSON.stringify({
                status: "ENTER",
                roomId: {roomId},
                sender: {usersName}
            }));
        }, (err) => {
            if(reconnect++ <= 5) {
                setTimeout(() => {
                    console.warn("connection reconnect");
                    sock = new SockJS("http://localhost:8080/ws/chat");
                    ws = Stomp.over(sock);
                    connect();
                }, 10 * 1000);
            }
        });
    }    
    
    useEffect(() => {
        // console.log(roomId);
        // console.log(usersName);
        findRoom();
        connect();
    }, []);

    return(
        <div className="ChatRoomDiv">
            <h1>This is {chatRoom.roomName}</h1>
            <div className="ChatMessageBoard">
                <ChatMessage msg="test01"/>
                <ChatMessage msg="test01R1"/>
            </div>
            <div className="ChatMessageInput">
                <input type="text" name="sendMessage" onChange={sendMessageChange} value={sendMessage} />
                <input type="button" name="sendMesssageBtn" onClick={sendMessageAction} value="보내기" />
            </div>
        </div>
    );
} 
export default ChatRoom;