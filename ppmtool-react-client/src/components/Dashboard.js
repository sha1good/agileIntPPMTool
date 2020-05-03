import React, { Component } from 'react'
import ProjectItem from './Project/ProjectItem';

class Dashboard extends Component {
    render() {
        return (
            <div>
                <h1 className="mr-1">Welcome to Dashbaord</h1>
                  <ProjectItem />
            </div>
        )
    }
}

export default Dashboard;
