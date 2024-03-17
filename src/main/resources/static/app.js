const roomId = 1;
const userId = Math.floor(Math.random() * 1000000) + '-' + (+new Date());

const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/sample-chatting'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/receive-message', (greeting) => {
        console.log("greeting: ", greeting);
        const message = JSON.parse(greeting.body);
        addMessage(message);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

async function connect() {
    await createUser();
    stompClient.activate();
    const messages = await fetchMessages();

    messages.forEach((message) => {
        addMessage(message);
    });

    const payload = {
        roomId,
        userId
    }

    await stompClient.publish({
        destination: "/app/enter",
        body: JSON.stringify(payload)
    });
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    const payload = {
        roomId,
        userId,
        content: $("#name").val()
    }

    stompClient.publish({
        destination: "/app/send-message",
        headers: {},
        body: JSON.stringify(payload)
    });
}

function addMessage(message) {
    const str = message.userId.split('-')[0] + ' : ' + message.content;
    $("#greetings").append("<tr><td>" + str + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
});

async function fetchMessages() {
    try {
        const endpoint = 'http://localhost:8080/api/v1/rooms/1/messages';
        const response = await fetch(endpoint);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error fetching messages:', error);
        return [];
    }
}

async function createUser() {
    try {
        const endpoint = 'http://localhost:8080/api/v1/users';
        const response = await fetch(endpoint, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ id: userId, name: userId })
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
    } catch (error) {
        console.error('Error creating user:', error);
        return {};
    }
}