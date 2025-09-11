import React, { } from 'react';
import './styles.css';
import { DashboardModuleTopBar } from '../../components/DashboardModuleTopBar/DashboardModuleTopBar';

const Loans: React.FC = () => {
    return (
        <div className='parent-container'>
            <DashboardModuleTopBar
                title="Préstamos"
                onExportClick={() => { }}
                onNewClick={() => { }}
            />
        </div>
    );
};

export default Loans;
