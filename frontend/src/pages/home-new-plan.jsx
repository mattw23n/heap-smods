import React, { useState } from "react";
import { Link } from 'react-router-dom';
import Header from "../components/header";
import Footer from "../components/footer";
import Background from "../components/background";
import TemplateUser from "../data/user";


const Content = ({ user, setUser }) => {

    const { name, plans, templates } = user

    const majors = [
        {Title: "Computer Science", Tracks: ["Artificial Intelligence", "Cybersecurity", "Cyberphysical-Systems", "Undeclared"]},
        {Title: "Information Systems", Tracks: ["Business Analytics", "Product Development", "Financial Technology", "Smart-City Management & Technology", "Undeclared"]},
        {Title: "Software Engineering", Tracks: ["Not Applicable"]},
        {Title: "Computing & Law", Tracks: ["Not Applicable"]},
    ]

    const [selectedTitle, setSelectedTitle] = useState("");
    const [selectedMajor, setSelectedMajor] = useState("");
    const [selectedTrack, setSelectedTrack] = useState("");

    const [errors, setErrors] = useState({
        title: "",
        major: "",
        track: "",
    });


    const handleMajorChange = (event) => {
        setSelectedMajor(event.target.value);
        
      };

    const handleTrackChange = (event) => {
        setSelectedTrack(event.target.value);
      };
    
    const handleTitleChange = (event) => {
        setSelectedTitle(event.target.value);
      };


    const handleSubmit = (e) => {
        e.preventDefault();
        {console.log(selectedMajor + selectedTrack + selectedTitle)}

        // Validate form
        const newErrors = {
            title: selectedTitle ? '' : 'Title is required',
            major: selectedMajor ? '' : 'Major is required',
            track: selectedTrack ? '' : 'Track is required',
        };

        setErrors(newErrors);

        // Check if there are no errors
        if (!newErrors.title && !newErrors.major && !newErrors.track) {
            // Form is valid, proceed with form submission
            console.log('Form submitted');
            // Your form submission logic here
        }
    };

    const selectedMajorTracks = majors.find((m) => m.Title === selectedMajor)?.Tracks || [];
    
    return(
        <div>
            <div className="mx-16 my-8 max-h-none max-w-screen flex-col gap-10">
                <div className="text-text font-poppins font-bold">
                        <p className="text-l">Good Afternoon</p>
                        <p className="text-3xl">{name}</p>
                </div>
                <div className="max-w-none py-4 flex gap-20">
                    <div className="flex flex-col gap-5">
                        <Link
                            className="flex rounded-xl w-32 bg-gray-500 px-6 py-3 justify-between align-center font-bold font-poppins text-l text-background transition 
                            hover:scale-102 hover:shadow-xl focus:outline-none focus:ring active:bg-indigo-500"
                            to="/"
                        >
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-6">
                            <path strokeLinecap="round" strokeLinejoin="round" d="M6.75 15.75 3 12m0 0 3.75-3.75M3 12h18" />
                            </svg>

                            <span>Back</span>
                        </Link>

                    </div>

                    <div className="max-w-none flex flex-col gap-2 text-text">
                        <p className="text-l font-poppins font-bold">🪄Create a New Plan</p>
                        <form className="isolate w-[600px] shadow-lg ring-1 ring-black/5 px-4 py-4 bg-white/50 rounded-3xl flex flex-col gap-5 text-text"
                        onSubmit={handleSubmit}>
                            <div>
                                <label htmlFor="PlanName" className="block text-xs font-bold font-poppins"> Name </label>

                                <input
                                    type="text"
                                    id="PlanName"
                                    placeholder="My first plan"
                                    className="mt-1 w-full rounded-xl border-gray-200 shadow-sm font-archivo sm:text-sm"
                                    value={selectedTitle}
                                    onChange={handleTitleChange}
                                />
                                {errors.title && <p className="text-red-500 text-xs mt-1">{errors.title}</p>}

                            </div>
                            <div>
                                <label htmlFor="PlanName" className="block text-xs font-bold font-poppins"> Major </label>

                                <select 
                                className="select mt-1 w-full rounded-xl border-gray-200 shadow-sm font-archivo sm:text-sm"
                                value={selectedMajor}
                                onChange={handleMajorChange}>
                                
                                <option disabled value="">Your Major</option>

                                    {majors.map((m, index) => (
                                        <option key={index} value={m.Title}>
                                        {m.Title}
                                        </option>
                                    ))}
                                </select>
                                {errors.major && <p className="text-red-500 text-xs mt-1">{errors.major}</p>}

                            </div>
                            <div>
                                <label htmlFor="PlanName" className="block text-xs font-bold font-poppins"> Track </label>

                                <select 
                                className="select mt-1 w-full rounded-xl border-gray-200 shadow-sm font-archivo sm:text-sm"
                                value={selectedTrack}
                                onChange={handleTrackChange}
                                disabled={!selectedMajor} >

                                <option disabled value="">Your Track</option>
                                    {selectedMajorTracks.map((track, index) => (
                                        <option key={index} value={track}>
                                        {track}
                                        </option>
                                    ))}
                            
                                </select>
                                {errors.track && <p className="text-red-500 text-xs mt-1">{errors.track}</p>}
                            </div>
                            <button
                                type="submit"
                                className="flex rounded-xl w-24 bg-secondary px-6 py-3 justify-center align-center font-bold font-poppins text-l text-background transition
                                hover:scale-102 hover:shadow-xl focus:outline-none focus:ring active:bg-indigo-500"
                            >
                                Create
                            </button>
                            
                        </form>
                        
                    </div>
                        
                    
                </div>
            </div>
            
        </div>
    )
}

function NewPlan(){

    const [user, setUser] = useState(
        TemplateUser
    );
    
    return (
        <div className="relative">
            <Background />
            <div className="relative z-10">
                <Header></Header>
                <Content user={user} setUser={setUser}></Content>
                <Footer></Footer>
            </div>
        </div>
    );
}

export default NewPlan;