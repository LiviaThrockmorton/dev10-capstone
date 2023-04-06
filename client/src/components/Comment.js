import { Link, useNavigate } from "react-router-dom";

import { findByOutfit } from "../services/commentService";
import Outfit from "./Outfit";
import { useContext, useEffect, useState } from "react";
import AuthContext from "../contexts/AuthContext";
import { findById } from "../services/OutfitService";


function Comment({ comment, commentUserId }) {

    const [outfit, setOutfit] = useState([]);
    const [outfitUser, setOutfitUser] = useState();
    const [error, setError] = useState(false);
    const { user } = useContext(AuthContext);

    const navigate = useNavigate();

    // const handleDelete = () => {
    //     navigate(`/delete/${comment.commentId}`)
    // };

    // const handleEdit = () => {
    //     navigate(`/edit/${comment.commentId}`)
    // };



    useEffect(() => {

        findById(comment.outfitId)
            .then(setOutfit)
            .catch(() => setError(true));

    }, [comment, navigate]);
    

    const username = commentUserId



//format date
    function getMonthName(monthNumber) {
        const date = new Date();
        date.setMonth(monthNumber - 1);

        return date.toLocaleString('en-US', { month: 'long' });
    }
    var date = new Date(comment.dateTime);
    date = getMonthName(date.getMonth()) + ' ' + date.getDay() + ', ' + date.getFullYear();





    return (

        <div className="card-container border-light" >
            <div className="card-header .bg-transparent text-white d-flex w-100 justify-content-between">
                    <h5 class="mb-1">{username}</h5>
                    <small>{date}</small>
            </div>
                <div className="card card-body">
                    <p>{comment.content}</p>
                </div>
        </div >



    );
}

export default Comment;