import http from 'k6/http';
import {sleep} from 'k6';

export const options = {
    vus: 10,
    duration: '20s',
};

export default function () {
    const url = 'http://localhost:8080/reserve';
    const payload = JSON.stringify({
        userId: 1,
        concertId: 1,
        concertSeatId: 1,
        seatNumber: 1
    });

    const params = {
        headers: {
            'accept': '*/*',
            'x-token': '88LMpDHgHsdIa8Vx1JJ1xjMBME7vk0eH/dRJ9VMmuLgfkhFaQoYBupISOgbb0Rjj',
            'Content-Type': 'application/json',
        },
    };

    const response = http.post(url, payload, params);

    console.log(`Response status: ${response.status}`);
    sleep(1);
}