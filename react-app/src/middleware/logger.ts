export default function loggerMiddleware(store: any) {
  return next => action => {
    return next(action);
  };
}
