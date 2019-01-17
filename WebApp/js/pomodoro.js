var clock;

//control variables
var workTime;
var breakTime;
var longBreakTime;
var cyclesCompleted;
var targetCycles;
var currentState;
var prestige;

//html elements
var longBreakDiv;
var workDiv;
var breakDiv;
var cyclesDiv;
var stateDiv;

$(document).ready(function () {
    init();
});

function init() {
    errorMsgDiv = $('#settings-message');
    workDiv = $('#work-time');
    breakDiv = $('#break-time');
    longBreakDiv = $('#long-break-time');
    cyclesDiv = $('#pomodoro-cycles');
    stateDiv = $('#state-div');

    $('.start').click(function (e) {
        clock.start();
    });

    $('.stop').click(function (e) {
        clock.stop();
    });

    $('.reset').click(function (e) {
        updateClock();
    });

    $('#saveButton').click(function (e) {
        updateClock();
    });

    prestige = 0;
    updateClock();
}

function updateClock() {
    isOnBreak = false;
    cyclesCompleted = 0;
    isClockRunning = false;
    workTime = workDiv.val() * 60;
    breakTime = breakDiv.val() * 60;
    longBreakTime = longBreakDiv.val() * 60;
    longBreakTime = longBreakDiv.val();
    targetCycles = cyclesDiv.val();
    cyclesCompleted = 0;
    currentState = 'work';
    stateDiv.text(`On Pomodoro cycle 1 out of ${targetCycles}`);

    initClock(workTime);
}

function displaySettingsError(wTime, bTime, pCycles) {
    var workError = false;
    var breakError = false;
    var cyclesError = false;

    if (Number.isInteger(wTime)) {
        workError = true;
    }
    if (Number.isInteger(bTime)) {
        bTime = true;
    }
    if (Number.isInteger(pCycles)) {
        pCycles = true;
    }

    if (!workError && !breakError && !cyclesError) {
        errorMsgDiv.text('Check your input(s)!');
    } else {
        errorMsgDiv.text('Unknown Error!');
    }
}

function initClock(time) {
    clock = $('.clock').FlipClock(time, {
        clockFace: 'MinuteCounter',
        countdown: true,
        autoStart: false,
        callbacks: {
            start: function () {
                //
            },
            stop: function () {
                var stopTime = clock.getTime();

                setTimeout(function () {
                    if (stopTime <= 0) {
                        handleTimerFinish();
                    }
                }, 1000)
            }
        }
    });
}

function handleTimerFinish() {
    if (currentState === 'work') {
        cyclesCompleted++;

        if (cyclesCompleted >= targetCycles) {
            currentState = 'longBreak';
            prestige++;
            cyclesCompleted = 0;
            initClock(longBreakTime);
            stateDiv.text('On a long break!');
        } else {
            currentState = 'break';
            initClock(breakTime);
            stateDiv.text(
                `On a break,${cyclesCompleted} out of ${targetCycles} completed`
            );
        }

    } else {
        currentState = 'work';
        initClock(workTime);
        stateDiv.text(`On Pomodoro cycle ${cyclesCompleted + 1} out of ${targetCycles}`);
    }
}

function getMethods(obj) {
    var result = [];
    for (var id in obj) {
        try {
            if (typeof (obj[id]) == "function") {
                result.push(id + ": " + obj[id].toString());
            }
        } catch (err) {
            result.push(id + ": inaccessible");
        }
    }
    return result;
}