import React, { useState } from 'react';
import Landing from './pages/landing.jsx';
import Planning from './pages/planning.jsx';
import Home from './pages/home.jsx';
import Loading from './pages/loading.jsx';
import NewPlan from './pages/home-new-plan.jsx';
import SignIn from './pages/sign_in.jsx';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import AboutUs from './pages/aboutUs.jsx';
import ContactUs from './pages/contactUs.jsx';
import Anims from './pages/justForAnims.jsx';

function App() {
    

    return (
        <Router>
            <Routes>
                <Route path="/home" element={ <Home />} />
                <Route exact path="/" element={ <Landing />} />
                <Route path="/new-plan" element={ <NewPlan />} />
                <Route path="/plan/:id" element={<Planning />} />
                <Route path="/signin" element={<SignIn />} />
                <Route path="/about" element={<AboutUs />} />
                <Route path="/contact" element={<ContactUs />} />
                <Route path="/anims" element={<Anims />} />
            </Routes>
        </Router>
    );
    
}

export default App;
