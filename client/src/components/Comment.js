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

    // const navigate = useNavigate();

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

    }, [comment.outfitId, navigate]);





    return (

        <div className="card border-primary w-25 m-3" >


            <div className="comment">
                <div className="username-date"> {comment.date}
                    <span className="username">{comment.commentUsername}</span>
                    <span className="date">April 2, 2023</span>
                </div>
                <div className="comment-text">
                    <p>{comment.content}</p>
                </div>
            </div>


            //Get the outfit

            {/* <div className="col-6">
                <div style={{ width: "800px", height: "1000px" }}>
                    {<Outfit key={outfit.outfitId} outfit={outfit} viewOutfit={false} />}
                </div>
            </div> */}
        </div >



    );
}





export default Comment;




//TODO

// get outfit userId
    //get user's username from userId
    //place in 'username here' spot

// get outfit and place where background image is

// get comments per outfit(comments has an outfitId section) - use a map
    // --Get comment userId 
    // --Get comment content
    // --Get comment date
    // --Display comment userId 
    // --Display comment content
    // --Display comment date

// make form for adding a comment save the comment onSubmit
