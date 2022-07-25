const ChatMessage = (props) => {
    return(
        <div className="ChatMessageDiv">
            <span id="ChatMessageContent">{props.msg}</span>
        </div>
    );
}
export default ChatMessage;