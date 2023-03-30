import { Link } from "react-router-dom";
import background from "./images/sink_ducklings.jpg";

function Home() {
    return (
        <div className="container w-100 h-100">
            <div className="row h-100">
                <div className="col-6">
                    <h1 className="text-center">Dress-Up Duck</h1>
                    <h5 className="text-center">Create new outfits, get inspiration from others, and more</h5>
                </div>

                <div className="h-100 col-6 bg-image" style={{ backgroundImage: `url(${background})`, backgroundPosition: "center", backgroundSize: "cover" }}>
                    <div className="d-flex align-items-center flex-column mt-5" style={{ backgroundColor: "rgba(255,255,255, .75)"}}>
                        <Link to="/create-account" className="btn btn-primary mb-4 mt-5">Sign Up</Link>
                        <Link to="/login" className="btn btn-primary mb-4">Log In</Link>
                        <Link to="/forum" className="btn btn-primary mb-4">Forum</Link>
                        <Link to="/dress-up-duck" className="btn btn-primary mb-4">Dress Up!</Link>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Home;