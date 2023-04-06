function Comment({ comment }) {

    function getMonthName(monthNumber) {
        const date = new Date();
        date.setMonth(monthNumber - 1);
        return date.toLocaleString('en-US', { month: 'long' });
    }

    var date = new Date(comment.dateTime);
    date = getMonthName(date.getMonth()) + ' ' + date.getDay() + ', ' + date.getFullYear();

    return (
        <div className="card-container border-light .bg-transparent " >
            <div className="card-header .bg-transparent text-white d-flex w-100 justify-content-between">
                <h5 className="mb-1">{comment.userId}</h5>
                <small>{date}</small>
            </div>
            <div className="card-body .bg-transparent">
                <p>{comment.content}</p>
            </div>
            <hr className="hr" />
            <div className="d-none">{comment.commentId}</div>
            <div className="d-none">{comment.userId}</div>
            <div className="d-none">{comment.outfitId}</div>
            <div className="d-none">{comment.hidden}</div>
        </div >
    );
}

export default Comment;