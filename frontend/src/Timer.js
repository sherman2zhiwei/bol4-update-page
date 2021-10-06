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

function textToCard(text){
    return (
        <div class="flex justify-center space-x-4 w-min mx-4">
            {text.split("").map(char => {
                return (
                    <div class="px-8 py-5 bg-red-500 shadow-md w-28 rounded-xl">
                        <p class="text-7xl font-medium font-body text-white">{char}</p>
                    </div>
                )
            })}
        </div>
    )


}

class Timer extends React.Component {
    constructor(props){
        super(props);
        this.state = { days: null }
    }

    componentDidMount(){
        fetch(uri)
            .then(response => response.json())
            .then(data => {
                console.log(data)
                this.setState({
                    name: data.name,
                    imageData: data.image.data,
                })
                this.interval = setInterval(() => {
                    let timeObject = msToDaysHoursMinutes(new Date - new Date(data.datetime))

                    this.setState({
                        days: timeObject.days,
                        hours: timeObject.hours,
                        minutes: timeObject.minutes,
                        seconds: timeObject.seconds,
                    })
                }, 1000)
            })
            .catch(error => console.log(error))
    }


    render(){
        return <>
            {this.state.days 
                ? 
                <div class="h-screen bg-fixed" style={{background: `url('data:image/jpg;base64,${this.state.imageData}')`}}>
                    <div class="bg-black h-full bg-opacity-75 flex flex-col justify-center">
                        <div class="xl:flex items-center justify-center py-4">
                            {["days", "hours", "minutes", "seconds"].map(text => {
                                return (
                                    <div class="flex items-center py-4 text-center">
                                        {textToCard(this.state[text].toString())}
                                        <p class="text-5xl font-body font-black text-white"> {text}, </p>
                                    </div> 
                                )
                            })

                            }
                        </div>

                        <p class="xl:text-center text-5xl mx-5 font-body font-black text-white"> since Bol4's Last Comeback : {this.state.name}</p> 
                    </div>
                </div>
                    
                : null
            }
        </>
    }
}

export default Timer;