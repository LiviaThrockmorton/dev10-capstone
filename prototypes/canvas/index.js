var canvas = document.getElementById("myCanvas");
var context = canvas.getContext("2d");

context.fillStyle = "#FF0000";
context.fillRect(0, 0, 150, 75);

context.moveTo(0, 0);
context.lineTo(200, 100);
context.stroke();

context.beginPath();
context.arc(50, 50, 40, 0, 2 * Math.PI);
context.stroke();

var linearGradient = context.createLinearGradient(50, 50, 200, 0);
linearGradient.addColorStop(1, "red");
linearGradient.addColorStop(0, "white");
context.fillStyle = linearGradient;
context.fillRect(50, 20, 150, 80);

var radialGradient = context.createRadialGradient(100, 50, 1, 90, 60, 100);
radialGradient.addColorStop(0, "yellow");
radialGradient.addColorStop(1, "white");
context.fillStyle = radialGradient;
context.fillRect(25, 10, 150, 80);

context.fillStyle = "black"
context.font = "25px Arial";
context.fillText("Hello World", 25, 50);

context.strokeText("Livia", 150, 100);

context.font = "50px Comic Sans MS";
context.fillStyle = "blue";
context.textAlign = "center";
context.fillText("Livia", canvas.width/2, canvas.height/2);

function draw() {
    var img = document.createElement('img');
    img.src = 'duck_life.jpg';
    context.drawImage(img, 10, 10);
}

draw();