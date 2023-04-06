import { findAppUser } from "../services/authService";
import { useEffect, useState } from "react";

function Comment({ comment }) {

    //Bea's changes -- I also added the imports at the top
    const [user, setUser] = useState([]);
    const [error, setError] = useState(false);

    function getMonthName(monthNumber) {
        const date = new Date();
        date.setMonth(monthNumber - 1);
        return date.toLocaleString('en-US', { month: 'long' });
    }

    var date = new Date(comment.dateTime);
    date = getMonthName(date.getDay()) + ' ' + (date.getMonth()+ 3) + ', ' + date.getFullYear();

//Bea's changes
    useEffect(() => {
        findAppUser(comment.userId)
            .then(setUser)
            .catch(() => setError(true));
        console.log(comment.userId);
    }, [comment.userId]);



//Bea's changes-- I also changed the user.username below in 2 places
    return (
        <div className="card-container border-light .bg-transparent " >
            <div className="card-header .bg-transparent text-white d-flex w-100 justify-content-between">
                <h5 className="mb-1">{user.username}</h5>
                <small>{date}</small>
            </div>
            <div className="card-body .bg-transparent">
                <p>{comment.content}</p>
            </div>
            <hr className="hr" />
            <div className="d-none">{user.username}</div>
            <div className="d-none">{comment.userId}</div>
            <div className="d-none">{comment.outfitId}</div>
            <div className="d-none">{comment.hidden}</div>
        </div >
    );
}

export default Comment;


