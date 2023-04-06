function Comment({ comment }) {

    function getMonthName(monthNumber) {
        const date = new Date();
        date.setMonth(monthNumber + 1);
        return date.toLocaleString('en-US', { month: 'long' });
    }

    var date = new Date(comment.dateTime);
    date = getMonthName(date.getMonth()) + ' ' + date.getDay() + ', ' + date.getFullYear();

    const commentUsername = comment.commentId


    // useEffect(() => {

    //     findUser(comment.appUserId)

    //         .then(setUser)

    //         .catch(() => setError(true));

    //     console.log(comment.appUserId);

    // }, [comment.appUserId]);


//    <img src={duck.duckImage} alt="duck" style={{ height: "150px", position: "relative", padding: "2px"}} />




    return (
        <div className="card-container border-light .bg-transparent " >
            <div className="card-header .bg-transparent text-white d-flex w-100 justify-content-between">
                <h5 className="mb-1">{commentUsername}</h5>
                <small>{date}</small>
            </div>
            <div className="card-body .bg-transparent">
                <p>{comment.content}</p>
            </div>
            <hr className="hr" />
            <div className="d-none">{commentUsername}</div>
            <div className="d-none">{comment.userId}</div>
            <div className="d-none">{comment.outfitId}</div>
            <div className="d-none">{comment.hidden}</div>
        </div >
    );
}

export default Comment;