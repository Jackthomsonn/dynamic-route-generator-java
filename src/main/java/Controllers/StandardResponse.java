package Controllers;

import Enums.StatusMessage;
import Enums.StatusResponse;

class StandardResponse
{

    StandardResponse( final StatusResponse statusResponse, final StatusMessage statusMessage )
    {
        final String status = this.getStatusResponse( statusResponse );
        final String message = this.getStatusMessage( statusMessage );
    }

    private String getStatusMessage( final StatusMessage statusMessage )
    {
        switch ( statusMessage )
        {
            case NO_RESOURCE_FOUND:
                return "No Resource Found";
            case INTERNAL_SERVER_ERROR:
                return "Internal Server Error";
            case ADDED_SUCCESSFULLY:
                return "Added Successfully";
            default:
                return "Unknown";
        }
    }

    private String getStatusResponse( final StatusResponse statusResponse )
    {
        switch ( statusResponse )
        {
            case ERROR:
                return "Error";
            case SUCCESS:
                return "Success";
            default:
                return "Unknown";
        }
    }
}