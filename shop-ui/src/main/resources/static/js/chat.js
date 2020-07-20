let stompClient = null;
let username = 'noname';
let receiver;

function connect() {
    receiver = '';
    let socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        if($('.is_admin').length){
            username = 'admin';
        }else{
            username = frame.headers['user-name'];
        }

        stompClient.subscribe('/user/chat_out/receive_message', function (payload) {
            let msg = JSON.parse(payload.body);
            receiver = msg.username;
            $("ul.chat").append(buildMessageHtml(msg.username, msg.message, false));
        });

        stompClient.subscribe('/chat_out/receive_message', function (payload) {
            let msg = JSON.parse(payload.body);
            $("ul.chat").append(buildMessageHtml(msg.username, msg.message, false));
        });
    });
}

function sendMessage() {
    let textInput = $("#btn-input");
    stompClient.send("/chat_in/send_message", {},
        JSON.stringify({
            'username' : username,
            'message': textInput.val(),
            'receiver': receiver
        }));
    $("ul.chat").append(buildMessageHtml(username, textInput.val(), true));
    textInput.val('');
}

function buildMessageHtml(username, message, direction) {
    return  '<li class="' + (direction ? 'left' : 'right') + ' clearfix">' +
        '<span class="chat-img ' + (direction ? 'pull-left' : 'pull-right') + '">' +
        '<img src="http://placehold.it/50/55C1E7/fff&text=U" alt="User Avatar" class="img-circle"/>' +
        '</span>' +
        '<div class="chat-body clearfix">' +
        '<div class="header">' +
        '<strong class="' + (direction ? 'pull-right' : '') + ' primary-font">' + username + '</strong>' +
        '</div>' +
        '<p>' + message + '</p>' +
        '</div>' +
        '</li>'
}

$(function () {
    $( "#btn-chat" ).click(function() { sendMessage(); });
});

connect();