import background from "./images/sink_ducklings.jpg";
import { Link } from "react-router-dom";
import { useState } from "react";

function CreateAccount() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [confirm, setConfirm] = useState("");
    const [email, setEmail] = useState("");
    let valid = false;

    function validateInput() {
        console.log(password, confirm);
        if (password !== confirm) {
            document.getElementById("validatePassword").classList.remove("d-none");
        } else {
            valid = true;
        }
    }

    const handleSubmit = async (evt) => {
        evt.preventDefault();
        validateInput();
        if (!valid) {
            return;
        }

        //await fetch post
    }

    return (
        <div className="container">
            <div className="flex-row w-50">
                <h1 className="text-center">Create Account</h1>
                <h5 className="text-center">Already have an account? Log in {' '}
                    <Link to="/login" className="btn btn-outline-secondary">here</Link>
                </h5>

                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="username">Username:</label>
                        <input type="text" onChange={(event) => setUsername(event.target.value)} className="form-control mb-4" id="username" placeholder="Enter a unique username" />
                    </div>

                    <div className="form-group">
                        <label htmlFor="password">Password:</label>
                        <input type="password" onChange={(event) => setPassword(event.target.value)} className="form-control mb-4" id="password" />
                    </div>

                    <div className="form-group">
                        <label htmlFor="confirm">Confirm Password:</label>
                        <input type="password" onChange={(event) => setConfirm(event.target.value)} className="form-control mb-4" id="confirm" />
                    </div>

                    <div className="form-group">
                        <label htmlFor="email">Email:</label>
                        <input type="enail" onChange={(event) => setEmail(event.target.value)} className="form-control mb-4" id="email" />
                    </div>

                    <button type="submit" className="btn btn-primary">Submit</button>
                </form>

                <p id="validatePassword" className="text-danger d-none">Passwords don't match, try again</p>
            </div>


            <div className="flex-row-reverse w-50 bg-image" style={{ backgroundImage: `url(${background})` }}>

            </div>
        </div>
    )
}

export default CreateAccount;