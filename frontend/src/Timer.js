import React from 'react'
const uri="http://localhost:8080/last-comeback-date"

function msToDaysHoursMinutes(ms){
    let days = Math.floor(ms / (24*60*60*1000));
    let daysms=ms % (24*60*60*1000);

    let hours = Math.floor((daysms)/(60*60*1000));
    let hoursms=ms % (60*60*1000);

    let minutes = Math.floor((hoursms)/(60*1000));
    let minutesms=ms % (60*1000);

    let seconds = Math.floor((minutesms)/(1000));
    return {days, hours, minutes, seconds};
}

class Timer extends React.Component {
    constructor(props){
        super(props);
        this.state = { datetime: null }
    }

    componentDidMount(){
        fetch(uri)
            .then(response => response.json())
            .then(data => {
                this.interval = setInterval(() => {
                    let timeObject = msToDaysHoursMinutes(new Date - new Date(data.datetime))

                    this.setState({
                        days: timeObject.days,
                        hours: timeObject.hours,
                        minutes: timeObject.minutes,
                        seconds: timeObject.seconds
                    })
                }, 1000)
            })
            .catch(error => console.log(error))
    }


    render(){
        return <>
            {this.state.days ? <h1>{`It has been ${this.state.days} days, ${this.state.hours} hours, ${this.state.minutes} minutes and ${this.state.seconds} seconds since Bol4's last comeback :(`}</h1> : <h2>Loading...</h2>}

        </>
    }
}

export default Timer;