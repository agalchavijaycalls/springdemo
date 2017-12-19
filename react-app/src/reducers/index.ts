import { combineReducers } from 'redux';
import todos from './todos';
import { routerReducer } from 'react-router-redux';

export interface RootState {
    todos: TodoStoreState;
}

export default combineReducers<RootState>({
    routing: routerReducer,
    todos
});
