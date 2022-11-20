var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#status").show();
    }
    else {
        $("#status").hide();
    }
}

function connect() {
    var socket = new SockJS('/market');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/volume', function (uve) {
            updateVolume(uve.body);
        });
        stompClient.subscribe('/topic/trades', function (trades) {
            addTrades(trades.body);
        });
    });
    socket.onclose = function(){setConnected(false)};
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function updateVolume(message) {
    $("#currentVolume").html("<tr><td>" + message.timestamp + ":" + message.operationsCount+ "</td></tr>");
}
function addTrades(trade) {
    jsonTrade = JSON.parse(trade)
    $("#trades").append("<tr>" +
    "<td>" + new Date(jsonTrade.timePlaced * 1000) + "</td>" +
    "<td>" + jsonTrade.currencyFrom + "</td>" +
    "<td>" + jsonTrade.currencyTo + "</td>" +
    "<td>" + jsonTrade.amountSell + "</td>" +
    "<td>" + jsonTrade.amountBuy + "</td>" +
    "<td>" + jsonTrade.rate + "</td>" +
    "<td>" + jsonTrade.originatingCountry + "</td>" +
    "</tr>");
}
function clearTrades(){
 $("#trades").html("");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#clear" ).click(function() { clearTrades(); });
    connect();
});

