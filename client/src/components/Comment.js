import { Link, useNavigate } from "react-router-dom";

import { findByOutfit } from "../services/commentService";
import Outfit from "./Outfit";
import { useContext, useEffect, useState } from "react";
import AuthContext from "../contexts/AuthContext";
import { findById } from "../services/OutfitService";


function Comment({ comment }) {

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

    





    return (

        <div className="card border-primary m-1" >


            <div className="comment">
                <div className="username-date"> 
                    <p className="username">{comment.commentUsername}</p>
                    <p className="date">{comment.date}</p>
                </div>
                <div className="comment-text">
                    <p>{comment.content}</p>
                </div>
            </div>
        </div >



    );
}

export default Comment;